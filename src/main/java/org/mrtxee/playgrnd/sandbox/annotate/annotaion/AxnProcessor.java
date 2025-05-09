package org.mrtxee.playgrnd.sandbox.annotate.annotaion;

import java.io.IOException;
import java.io.Writer;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

@SupportedAnnotationTypes("Axn") // Обрабатываем аннотацию @Axn
//@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class AxnProcessor extends AbstractProcessor {
  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    for (TypeElement annotation : annotations) {
      for (Element element : roundEnv.getElementsAnnotatedWith(annotation)) {
        if (element.getKind() == ElementKind.METHOD) {
          ExecutableElement method = (ExecutableElement) element;
          TypeElement classElement = (TypeElement) method.getEnclosingElement();

          try {
            generateWrapperClass(classElement, method);
          } catch (IOException e) {
            processingEnv.getMessager().printMessage(
              Diagnostic.Kind.ERROR,
              "Failed to generate wrapper for " + method);
          }
        }
      }
    }
    return true;
  }

  private void generateWrapperClass(TypeElement classElement, ExecutableElement method)
    throws IOException {
    String packageName = processingEnv.getElementUtils()
      .getPackageOf(classElement).getQualifiedName().toString();
    String className = classElement.getSimpleName().toString();
    String methodName = method.getSimpleName().toString();
    String wrapperClassName = className + "_" + methodName + "Wrapper";

    // Создаём новый исходный файл
    JavaFileObject file = processingEnv.getFiler()
      .createSourceFile(packageName + "." + wrapperClassName);

    try (Writer writer = file.openWriter()) {
      // Генерируем код класса-обёртки
      writer.write("package " + packageName + ";\n\n");
      writer.write("public class " + wrapperClassName + " {\n");
      writer.write("    public static void execute() {\n");
      writer.write("        System.out.println(\"[Axn] Before method " +
        methodName + "\");\n");
      writer.write("        try {\n");
      writer.write("            " + className + "." + methodName + "();\n");
      writer.write("            System.out.println(\"[Axn] After method " +
        methodName + "\");\n");
      writer.write("        } catch (Exception e) {\n");
      writer.write("            System.out.println(\"[Axn] Error in method " +
        methodName + ": \" + e.getMessage());\n");
      writer.write("        }\n");
      writer.write("    }\n");
      writer.write("}\n");
    }
  }

  private static String modifyResult(String original) {
    // Здесь вы можете изменить результат по своему усмотрению
    return "Modified: " + original;
  }
}