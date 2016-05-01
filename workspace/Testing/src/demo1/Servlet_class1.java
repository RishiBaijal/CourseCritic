package demo1;

public class Servlet_class1 {

	public String name;
	public String type;
	public String uploader;
	public String size;
	public String date;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Servlet_class1(String name, String type, String uploader, String size, String date) {

		this.name = name;
		this.type = type;
		this.uploader = uploader;
		this.size = size;
		this.date = date;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
