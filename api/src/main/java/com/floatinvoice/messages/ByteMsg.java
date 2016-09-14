package com.floatinvoice.messages;

public class ByteMsg extends BaseMsg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private byte [] bytes;
	private String fileName;
	
	public ByteMsg(){
		
	}
	
	public ByteMsg( byte[] bytes){
		System.arraycopy(bytes, 0, this.bytes = new byte [bytes.length], 0, bytes.length);
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		System.arraycopy(bytes, 0, this.bytes = new byte [bytes.length], 0, bytes.length);
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
