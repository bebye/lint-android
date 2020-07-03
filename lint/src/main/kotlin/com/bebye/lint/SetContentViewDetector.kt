package com.bebye.lint

import com.android.tools.lint.detector.api.*
import com.intellij.psi.PsiMethod
import org.jetbrains.uast.UCallExpression

/**
 * Created by mkwon on 03/07/2020.
 */
class SetContentViewDetector : Detector(), SourceCodeScanner {

    companion object {
        val ISSUE = Issue.create(
            id = SetContentViewDetector::class.java.simpleName,
            briefDescription = "Prohibits usages of SetContentView()",
            explanation = "Prohibits usages of SetContentView(), use DataBindingUtil.setContentView() instead",
            category = Category.CORRECTNESS,
            priority = 5,
            severity = Severity.WARNING,
            implementation = Implementation(SetContentViewDetector::class.java, Scope.JAVA_FILE_SCOPE))
    }

    // 반환된 리스트의 메소드명을 소스코드에서 검출
    override fun getApplicableMethodNames(): List<String>? {
        return listOf("setContentView")
    }

    // getApplicableMethodNames() 에서 반환된 메서드의 이름이 검출되면 호출됨
    override fun visitMethodCall(context: JavaContext, node: UCallExpression, method: PsiMethod) {
        if (context.evaluator.isMemberInClass(method, "androidx.databinding.DataBindingUtil")) {
            return
        }
        context.report(ISSUE, node, context.getLocation(node), "Use DataBindingUtil.setContentView() instead.")
    }

}