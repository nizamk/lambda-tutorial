package functional.lambda.dry;

/**
 * Created by nizamuddin on 29/04/2015.
 */

public class Email {
	String subject;
	String content;
	String sender;
	String recipient;

	public Email(String subject, String content, String sender, String recipient) {
		this.subject = subject;
		this.content = content;
		this.sender = sender;
		this.recipient = recipient;
	}

	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
}
