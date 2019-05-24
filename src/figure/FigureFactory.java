package figure;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class FigureFactory {
    private static List<Class<Figure>> figuresClasses = getFigures();

    public static List<Class<Figure>> getFigures() {
        List<Class<Figure>> figures = new ArrayList<>();
        String pathToJars = "JARS_T";
        File directory =  new File(Paths.get(pathToJars).toAbsolutePath().toString());
        File[] filesList = directory.listFiles();

        if (filesList == null) { return figures; }

        for (File jar: filesList) {
            if (jar.isFile() && jar.getName().endsWith(".jar")) {
                try {
                    JarFile jarFile = new JarFile(jar.getAbsolutePath());
                    Enumeration<JarEntry> enumeration = jarFile.entries();

                    URL[] urls = {new URL("jar:file:" + jar.getAbsolutePath() + "!/")};
                    URLClassLoader classLoader = URLClassLoader.newInstance(urls);

                    while (enumeration.hasMoreElements()) {
                        JarEntry jarEntry = enumeration.nextElement();
                        if(jarEntry.isDirectory() || !jarEntry.getName().endsWith(".class")){
                            continue;
                        }

                        String className = jarEntry.getName().substring(0,jarEntry.getName().length()-6);
                        className = className.replace('/', '.');
                        Class aClass = classLoader.loadClass(className);

                        if (Figure.class.isAssignableFrom(aClass) && !figures.contains(aClass)) {
                            figures.add((Class<Figure>) aClass);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return  figures;
    }

    public static Figure getFigure(String type) {
        for (Class<Figure> figureClass: figuresClasses) {
            if (figureClass.getName().endsWith(type)) {
                try {
                    Constructor constructor = figureClass.getConstructor();
                    return (Figure) constructor.newInstance();
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }
        }
        throw new RuntimeException();
    }
}
