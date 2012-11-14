package com.wuzhupc.mobile.interoperate;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Currency;

import com.google.gson.stream.JsonWriter;

/**
 * 基础类型转换
 * 包括：BigDecimal, BigInteger, boolean, Boolean, byte, Byte, 
 * char, Character, Currency, double, Double, float, Float, 
 * int, Integer, long, Long, short, Short, String
 */
public class BasicConverter implements Converter {

	public BasicConverter() {
		//
	}

	@Override
	public boolean canConvert(Class<?> type) {
		return type.equals(BigDecimal.class) || type.equals(BigInteger.class)
				|| type.equals(boolean.class) || type.equals(Boolean.class)
				|| type.equals(byte.class) || type.equals(Byte.class)
				|| type.equals(char.class) || type.equals(Character.class)
				|| type.equals(Currency.class) || type.equals(double.class)
				|| type.equals(Double.class) || type.equals(float.class)
				|| type.equals(Float.class) || type.equals(int.class)
				|| type.equals(Integer.class) || type.equals(long.class)
				|| type.equals(Long.class) || type.equals(short.class)
				|| type.equals(Short.class) || type.equals(String.class);
	}

	/**
	 * [name]:value
	 *	old:prop_[name]:value
	 */
	@Override
	public void marshal(Object source,JsonWriter writer,String ref)
	{
		try
		{
			writer.name(ref).value((source != null) ? source.toString() : VALUE_NULL);
			//writer.name(matchElement()+"_"+ref).value((source != null) ? source.toString() : VALUE_NULL);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
