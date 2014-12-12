package com.imaginea.warexecutor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.IOUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

@Mojo(name = "war-server-assembly")
public class WarServerAssemblyMojo extends AbstractMojo {

	@Parameter(defaultValue = "${project}", readonly = true)
	private MavenProject project;

	@Parameter(defaultValue = "${project.build.directory}/${project.artifactId}.executable.zip", readonly = true)
	private File archiveFile;

	@Parameter(required = true)
	private File warFile;

	@Parameter(defaultValue = "9191")
	private int defaultPort;

	public void execute() throws MojoExecutionException, MojoFailureException {
		try {
			cleanup();
			createZip();
		} catch (Exception e) {
			throw new MojoExecutionException("failed due to ", e);
		}
	}

	private void cleanup() throws IOException {
		if (this.archiveFile.exists()) {
			this.archiveFile.delete();
		}
	}

	private void copyTemplate(ZipArchiveOutputStream zout) throws IOException {

		JarFile jar = new java.util.jar.JarFile(this.getClass()
				.getProtectionDomain().getCodeSource().getLocation().getFile());
		try {
			@SuppressWarnings("rawtypes")
			Enumeration enumEntries = jar.entries();
			while (enumEntries.hasMoreElements()) {
				JarEntry file = (JarEntry) enumEntries.nextElement();
				if (!file.isDirectory() && file.toString().contains("template")) {
					InputStream is = jar.getInputStream(file);
					ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(file
							.toString().replace("template/", ""));
					zout.putArchiveEntry(zipArchiveEntry);
					IOUtils.copy(is, zout);
					if(is != null) {
						is.close();
					}
				}
			}
		} finally {
			if (jar != null) {
				jar.close();
			}
		}
	}

	private void createZip() throws IOException {
		if (!this.archiveFile.exists()) {
			this.archiveFile.createNewFile();
		}
		ZipArchiveOutputStream zout = null;
		try {
			zout = new ZipArchiveOutputStream(this.archiveFile);
			copyTemplate(zout);
			zout.putArchiveEntry(new ZipArchiveEntry(this.warFile, "app.war"));
			IOUtils.copy(new FileInputStream(this.warFile), zout);
		} finally {
			if (zout != null) {
				zout.closeArchiveEntry();
				zout.close();
			}

		}
	}
}