package hw9;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamUtils {
	/*
	 * Task 1: https://stepik.org/lesson/12781/step/14?unit=3128
	 * 
	 */

	// is in hw7

	/*
	 * Task2: https://stepik.org/lesson/12781/step/12?thread=solutions&unit=3128
	 * 
	 */
	public static <T> void findMinMax(Stream<? extends T> stream, Comparator<? super T> order,
			BiConsumer<? super T, ? super T> minMaxConsumer) {
		List<T> list = stream.collect(Collectors.toList());
		minMaxConsumer.accept(list.stream().min(order).orElse(null), list.stream().max(order).orElse(null));
	}

	/*
	 * Task 3: https://stepik.org/lesson/12781/step/11?unit=3128
	 * 
	 */
	public static IntStream pseudoRandomStream(int seed) {
		return IntStream.iterate(seed, n -> (n * n / 10) % 1000);
	}
}