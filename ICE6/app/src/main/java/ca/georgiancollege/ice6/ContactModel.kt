package ca.georgiancollege.ice6

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Represents a contact model.
 *
 * @param fullName The full name of the contact.
 * @param contactNumber The contact number of the contact.
 * @param emailAddress The email address of the contact.
 */
@JsonClass(generateAdapter = true)
data class ContactModel(
    @Json(name = "FullName") val fullName: String,
    @Json(name = "ContactNumber") val contactNumber: String,
    @Json(name = "EmailAddress") val emailAddress: String
)
{
    /**
     * Returns a string representation of the contact model.
     *
     * @return A string representation of the contact model.
     */
    override fun toString(): String
    {
        return """Full Name: $fullName, Contact Number: $contactNumber, Email Address: $emailAddress"""
    }
}
