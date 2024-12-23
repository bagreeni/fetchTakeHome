package com.bagreeni.fetchrewardstakehome.backend

/* As project gets larger, could make this an interface so all network calls follow
 * a similar pass-fail pattern.
*/
sealed class HiringResponse(){
    data class Success(
        val hiringList: List<FetchItem> = emptyList()
    ) : HiringResponse()
    // redundant code value here, but using as example for an additional context variable.
    data class ApiFailure(
        val code: Int
    ) : HiringResponse()
    data object Failure : HiringResponse()
    /*
     * I am using this pattern with the idea to create api-specific failure and success HiringResponse
     * objects to make front end interpretation easier. It creates a separation of concerns between
     * UI and Backend data handling if warranted.
     *
     * example: Where a coupon api has an expired object response vs invalid coupon response code. If front-end
     * doesn't decipher between the two, we still have the ability to log the difference in backend.
     * Or, on the flip side it gives front-end better state information.
    */
}