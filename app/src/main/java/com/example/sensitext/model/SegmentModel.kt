package com.example.sensitext.model

class SegmentModel (
    val agreement: String,
    val confidence: String,
    val endp: String,
    val inip: String,
    val polarity_term_list: List<PolarityItemModel>,
    val score_tag: String,
    val segment_type: String,
    val sentimented_concept_list: List<SentimentedElementModel>?,
    val sentimented_entity_list: List<SentimentedElementModel>?,
    val text: String
)