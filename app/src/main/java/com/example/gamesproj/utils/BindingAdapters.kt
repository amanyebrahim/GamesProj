package com.example.gamesproj.utils

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import coil.load
import com.example.gamesproj.R
import com.example.gamesproj.utils.extension.getParentActivity
import timber.log.Timber

/**
 * BindingAdapters
 *
 */

//region Binding properties
/**
 * Set [View] properties.
 */
@BindingAdapter(
    "mutableVisibility",
    "mutableEnable",
    "mutableAlpha",
    "mutableBackgroundId",
    requireAll = false
)
fun bindingAdapterView(
    view: View,
    @Nullable visibility: LiveData<Int?>?,
    @Nullable enabled: LiveData<Boolean?>?,
    @Nullable alpha: LiveData<Float?>?,
    @Nullable backgroundId: LiveData<Int?>?
) {
    view.getParentActivity()?.let { parentActivity ->
        visibility?.let { visibility ->
            visibility.observe(
                parentActivity,
                { value -> value?.let { view.visibility = it } }
            )
        }

        enabled?.let { enabled ->
            enabled.observe(
                parentActivity,
                { value -> value?.let { view.isEnabled = it } })
        }

        alpha?.let { alpha ->
            alpha.observe(
                parentActivity,
                { value -> value?.let { view.alpha = it } })
        }

        backgroundId?.let { backgroundId ->
            backgroundId.observe(
                parentActivity,
                { value -> value?.let { view.setBackgroundResource(it) } })
        }
    }
}

/**
 * Set [ImageView] properties.
 */
@BindingAdapter(
    "imageUrl",
    "imageUri",
    "imagePlaceholder",
    "mutableTintColorId",
    "mutableResourceId",
    requireAll = false
)
fun bindingAdapterImageView(
    view: ImageView,
    @Nullable imageUrl: LiveData<String?>?,
    @Nullable imageUri: LiveData<String?>?,
    @Nullable imagePlaceholder: Drawable?,
    @Nullable tintColorId: LiveData<Int?>?,
    @Nullable resourceId: LiveData<Int?>?
) {
    view.getParentActivity()?.let { parentActivity ->
        imageUrl?.let { imageUrl ->
            imageUrl.observe(parentActivity, { value ->
                value?.let {
                    when (it.isNotBlank()) {
                        true -> loadImageWithCoil(view, it, imagePlaceholder)

                        else -> {
                            when (imagePlaceholder) {
                                null -> view.setImageResource(R.drawable.ic_launcher_background)
                                else -> view.setImageDrawable(imagePlaceholder)
                            }
                        }
                    }
                }
            })
        }

        imageUri?.let { imageUri ->
            imageUri.observe(parentActivity, { value ->
                value?.let {
                    when (it.isNotBlank()) {
                        true -> view.setImageDrawable(Drawable.createFromPath(it))
                    }
                }
            })
        }

        tintColorId?.let { tintColorId ->
            tintColorId.observe(parentActivity, { value ->
                value?.let {
                    ImageViewCompat.setImageTintList(
                        view,
                        ColorStateList.valueOf(ContextCompat.getColor(parentActivity, it))
                    )
                }
            })
        }

        resourceId?.let { resourceId ->
            resourceId.observe(parentActivity, { value ->
                value?.let { view.setImageResource(it) }
            })
        }

        imagePlaceholder?.let { imagePlaceholder ->
            when {
                imageUrl?.value == null && imageUri?.value == null
                        && tintColorId?.value == null && resourceId?.value == null ->
                    view.setImageDrawable(imagePlaceholder)
            }
        }
    }
}

/**
 * Set [TextView] properties.
 */
@BindingAdapter(
    "mutableText",
    "mutableTextId",
    "mutableSpannableString",
    "mutableSpannableStringBuilder",
    "mutableTextColorId",
    requireAll = false
)
fun bindingAdapterTextView(
    view: TextView,
    @Nullable text: LiveData<String?>?,
    @Nullable textId: LiveData<Int?>?,
    @Nullable spannableString: LiveData<String?>?,
    @Nullable spannableStringBuilder: LiveData<SpannableStringBuilder?>?,
    @Nullable textColorId: LiveData<Int?>?
) {
    view.getParentActivity()?.let { parentActivity ->
        text?.let { text ->
            text.observe(parentActivity, { value -> value?.let { view.text = it } })
        }

        textId?.let { textId ->
            textId.observe(parentActivity, { value -> value?.let { view.setText(it) } })
        }

        spannableString?.let { spannableString ->
            spannableString.observe(
                parentActivity,
                { value -> value?.let { view.text = it.toString() } }
            )
        }

        spannableStringBuilder?.let { spannableStringBuilder ->
            spannableStringBuilder.observe(
                parentActivity,
                { value -> value?.let { view.setText(it, TextView.BufferType.SPANNABLE) } }
            )
        }

        textColorId?.let { textColorId ->
            textColorId.observe(
                parentActivity,
                { value ->
                    value?.let {
                        view.setTextColor(ContextCompat.getColor(parentActivity, it))
                    }
                }
            )
        }
    }
}
@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        loadImageWithCoil(view, imageUrl,null)
    }
}
//endregion

//region Private methods
/**
 * Load image with coil.
 */
private fun loadImageWithCoil(
    view: ImageView,
    imageUrl: String,
    imagePlaceholder: Drawable?
) {
    view.load(imageUrl) {
        apply {
            when (imagePlaceholder) {
                null -> {
                    placeholder(R.drawable.gradient)
                    error(R.drawable.gradient)
                }

                else -> {
                    placeholder(imagePlaceholder)
                    error(imagePlaceholder)
                }
            }
        }
        listener(onError = { _, throwable ->
            Timber.w(throwable, "Failed to load image: $imageUrl")
        })
    }
}
//endregion