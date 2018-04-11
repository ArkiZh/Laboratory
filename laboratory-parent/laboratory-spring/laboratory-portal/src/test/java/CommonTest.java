import com.arki.laboratory.common.Logger;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class CommonTest {
    @Test
    public void beanUtilTest() throws InvocationTargetException, IllegalAccessException {
        PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(Person.class);
        Person person = new Person();
        person.setName("Li Lei");
        person.setGender("");

        boolean b = validRequiredFields(person, new String[]{"name", "gender"});
        System.out.println(b);
    }

    /**
     * 校验实体类中的必填字段
     * @param t 要校验的实体
     * @param requiredFieldsArray 必填字段
     * @param <T>
     * @return true: 所有必填字段都有值，且如果为字符串时候不为空串或全空格字符串<BR/>
     *          false:必填字段中存在为null或trim后为""的字符串
     *
     */
    public static <T> boolean validRequiredFields(T t, String[] requiredFieldsArray) {
        if (requiredFieldsArray == null || requiredFieldsArray.length == 0) {
            return true;
        }
        Set<String> requiredFields = new HashSet<>();
        for (int i = 0; i < requiredFieldsArray.length; i++) {
            requiredFields.add(requiredFieldsArray[i]);
        }
        PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(t.getClass());
        int emptyCount = 0;
        for (int i = 0; i < pds.length; i++) {
            PropertyDescriptor pd = pds[i];
            String tempName = pd.getName();
            if (requiredFields.contains(tempName)) {
                Method readMethod = pd.getReadMethod();
                try {
                    Object result = readMethod.invoke(t);
                    if (result == null || result.toString().trim().equals("")) {
                        emptyCount++;
                        Logger.info("=============== 检测到必填字段“" + tempName + "”为空");
                    }
                } catch (IllegalAccessException e) {
                    Logger.info("=============== " + e.getLocalizedMessage(), e);
                } catch (InvocationTargetException e) {
                    Logger.info("=============== " + e.getLocalizedMessage(), e);
                }
                requiredFields.remove(tempName);
            }

        }
        return emptyCount == 0;
    }
}

class Person {
    private String name;
    private String gender;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

