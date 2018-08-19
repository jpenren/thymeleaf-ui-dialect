package io.github.thymeleaf.ui.dialect;

import java.util.Set;

import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.expression.IExpressionObjectFactory;

public class UiDialectExpressionObjectFactory implements IExpressionObjectFactory {

    @Override
    public Set<String> getAllExpressionObjectNames() {
        return null;
    }

    @Override
    public Object buildObject(IExpressionContext context, String expressionObjectName) {
        return null;
    }

    @Override
    public boolean isCacheable(String expressionObjectName) {
        return true;
    }

}
