package br.org.mcord.alpr4j.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;

import br.org.mcord.alpr4j.config.StreamGobbler;

@Service
public class AlprService {
	
	public String recognize(byte[] file, String filename) throws IOException, InterruptedException {
		
		Path path = saveFile(file, filename);
		
		String result = doAlpr("br", filename);
		
		path.toFile().delete();
		
		return result;
	}
	
	private Path saveFile(byte[] file, String name) throws IOException {
		Path path = Paths.get(name);
        return Files.write(path, file);
	}
	
	private String doAlpr(String c, String filename) throws IOException, InterruptedException {
		StringBuilder result = new StringBuilder();
		ProcessBuilder builder = new ProcessBuilder();
		builder.command("wsl", "alpr", "-c "+c, "-j", filename);
		builder.directory(new File(System.getProperty("user.dir")));
		Process process = builder.start();
		StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), result);
		Executors.newSingleThreadExecutor().submit(streamGobbler);
		int exitCode = process.waitFor();
		assert exitCode == 0;

		return result.toString();
	}

}
