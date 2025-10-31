package org.example.CommonValues.error;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedAnnotationTypes("com.example.ConvertibleTo")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class ConvertibleToProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(ConvertibleTo.class)) {
            ConvertibleTo annotation = element.getAnnotation(ConvertibleTo.class);
            TypeMirror fieldType = element.asType();
            TypeMirror targetType = processingEnv.getElementUtils()
                    .getTypeElement(annotation.value().getCanonicalName())
                    .asType();

            // You can add logic here to verify that your converter supports the conversion.
            // If not, emit an error:
            processingEnv.getMessager().printMessage(
                    Diagnostic.Kind.ERROR,
                    "Field " + element.getSimpleName() + " cannot be converted to " + targetType
            );
        }
        return true;
    }
}
