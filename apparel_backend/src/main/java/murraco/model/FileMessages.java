package murraco.model;

import org.springframework.web.multipart.MultipartFile;

public class FileMessages {
	private MultipartFile file;
	private String player;
	
	public FileMessages(MultipartFile file, String player) {
		super();
		this.file = file;
		this.player = player;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}
	
}
