package VisualLogicSystem.LogicBlocks;

import VisualLogicSystem.LogicBlock;

import VisualLogicSystem.LogicLibrary;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.List;

import java.net.URL;

import java.security.CodeSource;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import java.io.InputStream;

public class Library extends LogicLibrary {


    public Library() {

        /*
        System.out.println("VisualLogicSystem.LogicBlocks");
        Class[] blocks;
        try {
            blocks = getClasses("VisualLogicSystem.LogicBlocks");
            for (int i = 0; i < blocks.length; i++) {
                if (!blocks[i].getName().contains("Library")) {
                    System.out.println("" + blocks[i].getName());
                    try {
                        super.library.add((LogicBlock)blocks[i].newInstance());
                    } catch (InstantiationException e) {
                    } catch (IllegalAccessException e) {
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("oops");


        }
*/
        super.library.add(new LogicStart());
        super.library.add(new LogicEnd());

        /**
       * this.getClass().getProtectionDomain().getCodeSource()
       */


    }

    public class ZipClassLoader extends ClassLoader {
        private final ZipFile file;

        public ZipClassLoader(String filename) throws IOException {
            this.file = new ZipFile(filename);
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            ZipEntry entry =
                this.file.getEntry(name.replace('.', '/') + ".class");
            if (entry == null) {
                throw new ClassNotFoundException(name);
            }
            try {
                byte[] array = new byte[1024];
                InputStream in = this.file.getInputStream(entry);
                ByteArrayOutputStream out =
                    new ByteArrayOutputStream(array.length);
                int length = in.read(array);
                while (length > 0) {
                    out.write(array, 0, length);
                    length = in.read(array);
                }
                return defineClass(name, out.toByteArray(), 0, out.size());
            } catch (IOException exception) {
                throw new ClassNotFoundException(name, exception);
            }
        }
    }

    public static Class[] getClasses(String pckgname) throws ClassNotFoundException {
        ArrayList<Class> classes = new ArrayList<Class>();
        // Get a File object for the package
        File directory = null;
        try {
            ClassLoader cld = Thread.currentThread().getContextClassLoader();
            if (cld == null) {
                throw new ClassNotFoundException("Can't get class loader.");
            }
            String path = pckgname.replace('.', '/');
            URL resource = cld.getResource(path);
            if (resource == null) {
                throw new ClassNotFoundException("No resource for " + path);
            }
            directory = new File(resource.getFile());
        } catch (NullPointerException x) {
            throw new ClassNotFoundException(pckgname + " (" + directory +
                                             ") does not appear to be a valid package");
        }
        if (directory.exists()) {
            // Get the list of the files contained in the package
            String[] files = directory.list();
            for (int i = 0; i < files.length; i++) {
                // we are only interested in .class files
                if (files[i].endsWith(".class")) {
                    // removes the .class extension
                    classes.add(Class.forName(pckgname + '.' +
                                              files[i].substring(0,
                                                                 files[i].length() -
                                                                 6)));
                }
            }
        } else {
            throw new ClassNotFoundException(pckgname +
                                             " does not appear to be a valid package");
        }
        Class[] classesA = new Class[classes.size()];
        classes.toArray(classesA);
        return classesA;
    }
}
