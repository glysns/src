package com.digytal.control.infra.utils;

import com.digytal.control.webservice.modulo.cadastro.CadastroResource;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

public class Classes {
    public static void main(String[] args) {
        try {
            List<Class> c = classes(CadastroResource.class.getPackage().getName());
            System.out.println(c);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static List<String> classesName(String pacote) throws Exception{
        return classes(pacote).stream().map(c->c.getName()).collect(Collectors.toList());
    }
    public static List<Class> classes(String pacote) throws Exception{
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = pacote.replace('.', '/');
        Enumeration resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList();
        while (resources.hasMoreElements()) {
            URL resource = (URL) resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList classes = new ArrayList();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, pacote));
        }
        return classes;
    }
    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
