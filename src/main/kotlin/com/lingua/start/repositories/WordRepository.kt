package com.lingua.start.repositories

import com.lingua.start.models.Word
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WordRepository : JpaRepository<Word, Long>