package com.example.sensitext.model

class SentimentResponseModel (
    val agreement: String,
    val confidence: String,
    val irony: String,
    val model: String,
    val score_tag: String,
    val sentence_list: List<SentenceModel>,
    val sentimented_concept_list: List<SentimentedElementModel>,
    val sentimented_entity_list: List<SentimentedElementModel>,
    val status: StatusModel,
    val subjectivity: String
    )
