package top.youlanqiang.alphajson.utils;


import top.youlanqiang.alphajson.JSONArray;
import top.youlanqiang.alphajson.JSONException;
import top.youlanqiang.alphajson.bean.SimpleObjectBean;
import top.youlanqiang.alphajson.serialize.deobject.JSONDeserializer;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author youlanqiang
 * @version 1.0
 * @date 2018/10/8
 * @since 1.8
 * 变量转化工具类
 */
public class CastUtil {

    public static Byte castToByte(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof BigDecimal) {
            return byteValue((BigDecimal) value);
        }
        if (value instanceof Number) {
            return ((Number) value).byteValue();
        }
        if (value instanceof String) {
            String str = (String) value;
            if (StringUtil.isNullOrEmpty(str) || str.equals("NULL") || str.equals("null")) {
                return null;
            }
            return Byte.parseByte(str);
        }
        throw new JSONException("The value cast to byte error");
    }

    public static Byte byteValue(BigDecimal value) {
        if (value == null) {
            return 0;
        }
        return value.byteValue();
    }


    public static Short castToShort(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof BigDecimal) {
            return shortValue((BigDecimal) value);
        }
        if (value instanceof Number) {
            return ((Number) value).shortValue();
        }
        if (value instanceof String) {
            String str = (String) value;
            if (StringUtil.isNullOrEmpty(str) || str.equals("NULL") || str.equals("null")) {
                return null;
            }
            return Short.parseShort(str);
        }
        throw new JSONException("The value cast to short error");
    }

    public static Short shortValue(BigDecimal value) {
        if (value == null) {
            return 0;
        }
        return value.shortValue();
    }


    public static Character castToChar(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Character) {
            return (char) value;
        }
        if (value instanceof String) {
            String str = (String) value;
            if (StringUtil.isNullOrEmpty(str)) {
                return '\0';
            }
            if (str.length() == 1) {
                return str.charAt(0);
            }
        }
        throw new JSONException("The value cast to char error");
    }

    public static Integer castToInteger(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof BigDecimal) {
            return intValue((BigDecimal) value);
        }
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        if (value instanceof String) {
            String str = (String) value;
            if (StringUtil.isNullOrEmpty(str) || str.equals("NULL") || str.equals("null")) {
                return null;
            }
            if (str.contains(".")) {
                str = str.substring(str.indexOf('.'));
                return Integer.valueOf(str);
            }
            return Integer.valueOf(str);
        }
        throw new JSONException("The value cast to int error");
    }

    public static Integer intValue(BigDecimal value) {
        if (value == null) {
            return 0;
        }
        return value.intValue();
    }

    public static Long castToLong(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof BigDecimal) {
            return longValue((BigDecimal) value);
        }
        if (value instanceof Number) {
            return ((Number) value).longValue();
        }
        if (value instanceof String) {
            String str = (String) value;
            if (StringUtil.isNullOrEmpty(str) || str.equals("NULL") || str.equals("null")) {
                return null;
            }
            if (str.contains(".")) {
                str = str.substring(str.indexOf('.'));
                return Long.valueOf(str);
            }
            return Long.valueOf(str);
        }
        throw new JSONException("The value cast to long error");
    }

    public static Long longValue(BigDecimal value) {
        if (value == null) {
            return 0L;
        }
        return value.longValue();
    }

    public static Float castToFloat(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof BigDecimal) {
            return floatValue((BigDecimal) value);
        }
        if (value instanceof Number) {
            return ((Number) value).floatValue();
        }
        if (value instanceof String) {
            String str = (String) value;
            if (StringUtil.isNullOrEmpty(str) || str.equals("NULL") || str.equals("null")) {
                return null;
            }
            return Float.valueOf(str);
        }
        throw new JSONException("The value cast to float error");
    }

    public static Float floatValue(BigDecimal value) {
        if (value == null) {
            return 0F;
        }
        return value.floatValue();
    }

    public static Double castToDouble(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof BigDecimal) {
            return doubleValue((BigDecimal) value);
        }
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        if (value instanceof String) {
            String str = (String) value;
            if (StringUtil.isNullOrEmpty(str) || str.equals("NULL") || str.equals("null")) {
                return null;
            }
            return Double.valueOf(str);
        }
        throw new JSONException("The value cast to int error");
    }

    public static Double doubleValue(BigDecimal value) {
        if (value == null) {
            return 0.0;
        }
        return value.doubleValue();
    }


    public static String castToString(Object value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

    public static Boolean castToBoolean(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof BigDecimal) {
            int num = intValue((BigDecimal) value);
            return num != 0 ? true : false;
        }
        if (value instanceof Number) {
            int num = castToInteger(value);
            return num != 0 ? true : false;
        }
        if (value instanceof String) {
            String str = (String) value;
            if (StringUtil.isNullOrEmpty(str) || str.equals("NULL") || str.equals("null")) {
                return null;
            }
            return Boolean.valueOf(str);
        }
        if(value instanceof Boolean){
            return (boolean)value;
        }
        throw new JSONException("The value cast to boolean error");
    }


    public static Object castToObject(String value) {
        if (value.length() == 0) {
            return value;
        }
        char start = value.charAt(0);
        //判断类型为String, false, true, null类型

        switch (start) {
            case '\"':
                return value.substring(1);
            case '\'':
                return value.substring(1);
            case 'f':
                return false;
            case 't':
                return true;
            case 'n':
                return null;
            case '{':
                return JSONDeserializer.parseToObject(value);
            case '[':
                return JSONDeserializer.parseToObject(value);
        }
        try {
            if (value.contains(".")) {
                return Double.valueOf(value);
            } else {
                return Long.valueOf(value);
            }
        } catch (Exception e) {
            return value;
        }
    }


    public static <T> T cast(Object obj, Class<T> clazz) {
        if (obj == null) {
            if (clazz == int.class) {
                return (T) Integer.valueOf(0);
            }
            if (clazz == long.class) {
                return (T) Long.valueOf(0);
            }
            if (clazz == float.class) {
                return (T) Float.valueOf(0.0f);
            }
            if (clazz == double.class) {
                return (T) Double.valueOf(0.0);
            }
            if (clazz == byte.class) {
                return (T) Byte.valueOf((byte) 0);
            }
            if (clazz == short.class) {
                return (T) Short.valueOf((short) 0);
            }
            if(clazz == boolean.class){
                return (T) Boolean.FALSE;
            }
            return null;
        }
        if(clazz == null){
            throw new NullPointerException("class is null");
        }

        if(obj.getClass() == clazz){
            return (T) obj;
        }

        if(clazz.isAssignableFrom(obj.getClass())){
            return (T) obj;
        }

        if(clazz.isArray()){
            if(obj instanceof Collection){
                Collection coll = (Collection) obj;
                Object array = Array.newInstance(clazz.getComponentType(), coll.size());
                int index = 0;
                for(Object item : coll){
                    Array.set(array, index, cast(item, clazz.getComponentType()));
                    index++;
                }
                return (T) array;
            }
        }

        if(clazz == Map.class){
            if(obj instanceof Map){
                return (T) obj;
            }
            SimpleObjectBean<Object> bean = new SimpleObjectBean<>(obj);
            return (T) bean.getContainer();
        }

        //TODO 后续转化执行
        if(clazz == int.class || clazz == Integer.class){
            return (T) castToInteger(obj);
        }
        if(clazz == long.class || clazz == Long.class){
            return (T) castToLong(obj);
        }
        if(clazz == float.class || clazz == Float.class){
            return (T) castToFloat(obj);
        }
        if(clazz == double.class || clazz == Double.class){
            return (T) castToDouble(obj);
        }
        if(clazz == short.class || clazz == Short.class){
            return (T) castToShort(obj);
        }
        if(clazz == byte.class || clazz == Byte.class){
            return (T) castToByte(obj);
        }
        if(clazz == char.class || clazz == Character.class){
            return (T) castToChar(obj);
        }
        if(clazz == boolean.class || clazz == Boolean.class){
            return (T) castToBoolean(obj);
        }
        if(clazz == String.class){
            return (T) castToString(obj);
        }

        return null;
    }


}
