package fr.utbm.gl52.stack.tests;

import fr.utbm.gl52.stack.AbstractStack;
import fr.utbm.gl52.stack.StackListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;
import static fr.utbm.gl52.util.ReflectUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;

/** Test of {@code AbstractStack}.
 *
 * @author sgalland
 */
@SuppressWarnings({"javadoc", "nls"})
public class AbstractStackTest {
	
	private AbstractStack<String> data;
	
	@BeforeEach
	@SuppressWarnings("unchecked")
	public void setUp() {
		this.data = mock(AbstractStack.class);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void addStackListener_dataAdded() throws Exception {
		StackListener listener0 = mock(StackListener.class);
		this.data.addStackListener(listener0);

		StackListener listener1 = mock(StackListener.class);
		this.data.addStackListener(listener1);

		invokeProc(this.data, "fireDataAdded", new Class[] {Object.class}, "abc");

		final ArgumentCaptor<AbstractStack<String>> arg0 = ArgumentCaptor.forClass(AbstractStack.class);
		final ArgumentCaptor<Object> arg1 = ArgumentCaptor.forClass(Object.class);
		verify(listener0, times(1)).dataAdded(arg0.capture(), arg1.capture());
		assertSame(this.data, arg0.getValue());
		assertEquals("abc", arg1.getValue());

		verify(listener0, never()).dataRemoved(any(), any());

		verify(listener1, times(1)).dataAdded(arg0.capture(), arg1.capture());
		assertSame(this.data, arg0.getValue());
		assertEquals("abc", arg1.getValue());

		verify(listener1, never()).dataRemoved(any(), any());
	}

	public void addStackListener_dataRemoved() {
	}

	public void removeStackListener() {
	}

	public void iterator() {
	}

}
