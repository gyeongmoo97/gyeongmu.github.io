package workspace_th.day06.quiz1;

import java.time.LocalDate;
import java.util.Date;

public class Video {
	String title, category, lendName;
	Boolean lend;
	LocalDate lendDate = LocalDate.now();
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getLendName() {
		return lendName;
	}
	public void setLendName(String lendName) {
		this.lendName = lendName;
	}
	public Boolean getLend() {
		return lend;
	}
	public void setLend(Boolean lend) {
		this.lend = lend;
	}
	public LocalDate getLendDate() {
		return lendDate;
	}
	public void setLendDate(LocalDate lendDate) {
		this.lendDate = lendDate;
	}
	
	
	public Video() {
		super();
	}
	public Video(String title, String category, String lendName, Boolean lend) {
		super();
		this.title = title;
		this.category = category;
		this.lendName = lendName;
		this.lend = lend;
		
	}
	
	
	
}
