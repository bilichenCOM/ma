package hw6;

import java.util.*;
import java.util.function.*;

public class AccountantOffice {

	public interface Sendable<T> {
		String getFrom();

		String getTo();

		T getContent();
	}

	public static class MailMessage implements Sendable<String> {
		private final String from;
		private final String to;
		private final String content;

		public MailMessage(String from, String to, String content) {
			this.from = from;
			this.to = to;
			this.content = content;
		}

		public String getFrom() {
			return from;
		}

		public String getTo() {
			return to;
		}

		public String getContent() {
			return content;
		}

	}

	public static class Salary implements Sendable<Integer> {
		private String from;
		private String to;
		private Integer content;

		public Salary(String from, String to, Integer content) {
			this.from = from;
			this.to = to;
			this.content = content;
		}

		@Override
		public String getFrom() {
			return from;
		}

		@Override
		public String getTo() {
			return to;
		}

		@Override
		public Integer getContent() {
			return content;
		}

	}

	public static class MailService<T> implements Consumer<Sendable<T>> {
		private Map<String, List<T>> mailBox;

		public MailService() {
			mailBox = new MailBox<>();
		}

		public Map<String, List<T>> getMailBox() {
			return mailBox;
		}

		public void accept(Sendable<T> message) {
			addToMailBox(message);
		}

		private void addToMailBox(Sendable<T> message) {
			String to = message.getTo();
			T content = message.getContent();
			List<T> contentList = Optional.ofNullable(mailBox.get(to)).orElse(new LinkedList<>());
			contentList.add(content);
			mailBox.put(to, contentList);
		}
	}

	public static class MailBox<K, V> extends HashMap<K, V> {
		private static final long serialVersionUID = 1L;

		@SuppressWarnings("unchecked")
		@Override
		public V get(Object key) {
			return Optional.ofNullable(super.get(key)).orElse((V) new ArrayList<>());
		}
	}
}