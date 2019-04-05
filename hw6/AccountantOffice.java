package hw6;

import java.util.*;
import java.util.function.*;

public class AccountantOffice {

	public static abstract class Sendable<T> {
		private final String from;
		private final String to;
		private final T content;

		public Sendable(String from, String to, T content) {
			super();
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

		public T getContent() {
			return content;
		}
	}

	public static class MailMessage extends Sendable<String> {
		public MailMessage(String from, String to, String content) {
			super(from, to, content);
		}
	}

	public static class Salary extends Sendable<Integer> {
		public Salary(String from, String to, Integer content) {
			super(from, to, content);
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
			List<T> contentList = mailBox.get(to);
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