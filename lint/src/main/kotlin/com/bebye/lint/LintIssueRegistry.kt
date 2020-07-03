package com.bebye.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API

/**
 * Created by mkwon on 03/07/2020.
 */
class LintIssueRegistry : IssueRegistry() {

    // lint api
    override val api = CURRENT_API

    override val issues = listOf(SetContentViewDetector.ISSUE)

}