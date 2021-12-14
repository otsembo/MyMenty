package com.eeyan.mymenty.data.remote.dto

import com.eeyan.mymenty.domain.model.HealthTip

data class Resource(
    val AccessibleVersion: String,
    val Categories: String,
    val HealthfinderLogo: String,
    val HealthfinderUrl: String,
    val Id: String,
    val ImageAlt: String,
    val ImageUrl: String,
    val LastUpdate: String,
    val MoreInfoItems: Any,
    val MyHFCategory: String,
    val MyHFCategoryHeading: String,
    val MyHFDescription: String,
    val MyHFTitle: String,
    val Populations: String,
    val RelatedItems: RelatedItems,
    val Sections: Sections,
    val Title: String,
    val TranslationId: String,
    val TranslationTitle: String,
    val Type: String
)
//convert to health tip
fun Resource.getHealthTip() : HealthTip{
    return HealthTip(id = Id, title = Title, desc = MyHFDescription, image = ImageUrl, url = AccessibleVersion)
}