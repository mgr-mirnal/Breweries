package com.example.brewery.repository



import com.example.brewery.common.ResponseState
import com.example.brewery.data.repository.BreweryRepositoryImpl
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test

class RepositoryImplTest {

    @MockK
     lateinit var vmaRepository: BreweryRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this) //for initialization
    }

    @Test
    fun getPeopleData() = runBlocking {
        val breweryList = mockk<Flow<ResponseState>>()
        every { runBlocking { vmaRepository.getBrewery() } } returns (breweryList)

        val result = vmaRepository.getBrewery()
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$breweryList] must be matches on each other!",
            result,
            CoreMatchers.`is`(breweryList)
        )
    }


}