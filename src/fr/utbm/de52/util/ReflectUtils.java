package fr.utbm.de52.util;

import java.lang.reflect.Method;
import java.util.NoSuchElementException;

/** Provide additional utilities for calling functions that are not visible.
 *
 * <p>This class could be found in Guava library.
 * 
 * @author sgalland
 */
public final class ReflectUtils {

	private ReflectUtils() {
		//
	}

	/** Call the function with the given name on the given instance.
	 *
	 * <p>CAUTION: This method can access to the functions that are public, or defined into the same Java module.
	 *
	 * @param instance the instance of the object. It cannot be {@code null}.
	 * @param name the name of the function to invoke, whatever its visibility.
	 * @param parameters the types of the formal parameters of the function.
	 * @param arguments the arguments to pass to the function.
	 * @throws Exception if the function cannot be invoked.
	 */
	public static void invokeProc(Object instance, String name, Class<?>[] parameters, Object... arguments) throws Exception {
		var type = instance.getClass();
		Method method;
		do {
			try {
				method = type.getDeclaredMethod(name, parameters);
			} catch (NoSuchMethodException exception) {
				// Ignore this exception in order to move to the super type.
				method = null;
			}
			type = type.getSuperclass();
		} while (method == null && type != null);
		if (method == null) {
			throw new NoSuchElementException(name);
		}
		// The following line works only within the current module (restriction since Java 9)
		method.setAccessible(true);
		method.invoke(instance, arguments);
	}

}