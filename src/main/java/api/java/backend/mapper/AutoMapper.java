package api.java.backend.mapper;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class AutoMapper<T, S> {

    public void copiar(T destino, S origem) {
        try {
            BeanUtils.copyProperties(destino, origem);
            for (Field f : origem.getClass().getDeclaredFields()){
                f.setAccessible(true);
                if (List.class.isAssignableFrom(f.getType())){
                    List<?> listaOrigem = (List<?>) f.get(origem);
                    List<Object> listaDestino = new ArrayList<>();
                    for (Object objetoOrigem : listaOrigem){
                        Object objetoDestino = objetoOrigem.getClass().newInstance();
                        copiar((T)objetoDestino, (S)objetoOrigem);
                        listaDestino.add(objetoDestino);
                    }

                }
            }
        }catch (IllegalAccessException | InstantiationException e){

        }

    }

    public static <T> List<T> copyListProperties(List<?> sourceList, Class<T> targetClass) {
        List<T> targetList = new ArrayList<>();
        for (Object source : sourceList) {
            T target = null;
            try {
                target = targetClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            BeanUtils.copyProperties(source, target);
            targetList.add(target);
        }
        return targetList;
    }

    public static <T> T copyProperties(Object source, Class<T> targetClass) {
        T target = null;
        try {
            target = targetClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        BeanUtils.copyProperties(source, target);
        return target;
    }


}
