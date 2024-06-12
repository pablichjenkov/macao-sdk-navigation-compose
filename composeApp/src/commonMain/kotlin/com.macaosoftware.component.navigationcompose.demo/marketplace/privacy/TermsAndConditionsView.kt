package com.macaosoftware.component.navigationcompose.demo.marketplace.privacy

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight

@Composable
fun TermsAndConditionsView() {
    val fullText = "By clicking Accept, you agree to our Privacy Policy and Terms of Service."
    val tags = listOf(
        mapOf("text" to "Privacy Policy", "tag" to "privacy", "url" to "https://composables.com/privacy"),
        mapOf("text" to "Terms of Service", "tag" to "terms", "url" to "https://composables.com/terms"),
    )

    val annotatedString = buildAnnotatedString {
        append(fullText)
        tags.forEach { tag ->
            val text = tag["text"] ?: return@forEach
            val url = tag["url"] ?: return@forEach
            val tagText = tag["tag"] ?: return@forEach
            val start = fullText.indexOf(text)
            val end = start + text.length

            addStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold), start = start, end = end)

            addStringAnnotation(tag = tagText, annotation = url, start = start, end = end)
        }
    }
    val uriHandler = LocalUriHandler.current
    ClickableText(style = MaterialTheme.typography.bodyLarge, text = annotatedString, onClick = { offset ->
        tags.firstNotNullOfOrNull { it ->
            val tag = it["tag"] ?: return@firstNotNullOfOrNull null
            annotatedString.getStringAnnotations(tag, offset, offset).firstOrNull()
        }?.let { string ->
            uriHandler.openUri(string.item)
        }
    })
}
