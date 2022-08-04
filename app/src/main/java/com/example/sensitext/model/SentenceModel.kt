package com.example.sensitext.model

class SentenceModel (
    val agreement: String,
    val bop: String,
    val confidence: String,
    val endp: String,
    val inip: String,
    val score_tag: String,
    val segment_list: List<SegmentModel>,
    val sentimented_concept_list: List<SentimentedElementModel>,
    val sentimented_entity_list: List<SentimentedElementModel>,
    val text: String
)