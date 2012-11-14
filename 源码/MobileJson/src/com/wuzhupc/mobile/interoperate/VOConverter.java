package com.wuzhupc.mobile.interoperate;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.stream.JsonWriter;

public class VOConverter implements Converter {

	private ConvertLookup convertLookup;

	public VOConverter(ConvertLookup convertLookup) {
		this.convertLookup = convertLookup;
	}

	@Override
	public boolean canConvert(Class<?> type) {
		return true;
	}

	/**
	 * [class]_[ref]:{}
	 * odl:entity_[class]_[ref]:{}
	 */
	@Override
	public void marshal(Object source, JsonWriter writer, String ref)
	{
		//String name=matchElement()+"_"+source.getClass().getSimpleName();
		String name=source.getClass().getSimpleName();
		if (ref != null && !ref.equals(""))
			name=name+"_"+ref;
		try
		{
			writer.name(name);
			writer.beginObject();
			convertOther(source,writer);
			writer.endObject();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 将对象整理为JSON，添加到父节点中
	 * @param object 待处理对�?
	 * @param parent 父节�?
	 */
	private void convertOther(Object object, JsonWriter parent) {
		for (Field field : buildField(object.getClass())) {
			if (!fieldModifiersSupported(field)) {
				continue;
			}
			validateFieldAccess(field);
			try {
				Object value = field.get(object);
				convertLookup.convert(value, parent, field.getName());
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 取出class中field
	 * @param type
	 * @return class field list
	 */
	private List<Field> buildField(final Class<?> type) {
		Class<?> cls = type;
		List<Field> list = new ArrayList<Field>();
		synchronized (this) {
			final List<Class<?>> superClasses = new ArrayList<Class<?>>();
			while (!Object.class.equals(cls)) {
				superClasses.add(0, cls);
				cls = cls.getSuperclass();
			}

			for (final Iterator<Class<?>> iter = superClasses.iterator(); iter
					.hasNext();) {
				cls = (Class<?>) iter.next();
				Field[] fields = cls.getDeclaredFields();
				// JVM1.4 or IBM JDK
				if (fields.length == 0) {
					for (int i = fields.length >> 1; i-- > 0;) {
						final int idx = fields.length - i - 1;
						final Field field = fields[i];
						fields[i] = fields[idx];
						fields[idx] = field;
					}
				}

				for (int i = 0; i < fields.length; i++) {
					Field field = fields[i];
					field.setAccessible(true);
					list.add(field);
				}
			}
		}
		return list;
	}

	/**
	 * field是否是static或�?transient变量
	 * @param field
	 * @return
	 */
	private boolean fieldModifiersSupported(Field field) {
		return !(Modifier.isStatic(field.getModifiers()) || Modifier
				.isTransient(field.getModifiers()));
	}

	/**
	 * 
	 * @param field
	 */
	private void validateFieldAccess(Field field) {
		if (Modifier.isFinal(field.getModifiers())) {
			field.setAccessible(true);
		}
	}

}
