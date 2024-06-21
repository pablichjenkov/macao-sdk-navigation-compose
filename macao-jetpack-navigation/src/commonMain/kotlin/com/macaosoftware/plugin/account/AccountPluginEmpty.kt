package com.macaosoftware.plugin.account

import com.macaosoftware.util.MacaoResult
import kotlin.native.ObjCName

/**
 * An empty implementation for those platforms that don't have Firebase.
 * */
@ObjCName(name = "MacaoAccountPluginEmpty", exact = true)
class AccountPluginEmpty : AccountPlugin {
    override suspend fun initialize(): Boolean {
        println(" AuthPluginEmpty::initialize() has been called")
        return true
    }

    override suspend fun createUserWithEmailAndPassword(signUpRequest: SignUpRequest): MacaoResult<MacaoUser, AccountError> {
        println(" AuthPluginEmpty::createUserWithEmailAndPassword() has been called")
        return MacaoResult.Success(
            MacaoUser("test@gmail.com")
        )
    }

    override suspend fun signInWithEmailAndPassword(signInRequest: SignInRequest): MacaoResult<MacaoUser, AccountError> {
        println(" AuthPluginEmpty::signInWithEmailAndPassword() has been called")
        return MacaoResult.Success(
            MacaoUser("test@gmail.com")
        )
    }

    override suspend fun signInWithEmailLink(signInRequest: SignInRequestForEmailLink): MacaoResult<MacaoUser, AccountError> {
        println(" AuthPluginEmpty::signInWithEmailLink() has been called")
        return MacaoResult.Success(MacaoUser("empty@gmail.com"))
    }

    override suspend fun sendSignInLinkToEmail(emailLinkData: EmailLinkData): MacaoResult<Unit, AccountError> {
        println(" AuthPluginEmpty::sendSignInLinkToEmail() has been called")
        return MacaoResult.Success(Unit)
    }

    override suspend fun getCurrentUser(): MacaoResult<MacaoUser, AccountError> {
        println(" AuthPluginEmpty::getCurrentUser() has been called")
        return MacaoResult.Success(MacaoUser("empty@gmail.com"))
    }

    override suspend fun getProviderData(): MacaoResult<ProviderData, AccountError> {
        println(" AuthPluginEmpty::getProviderData() has been called")
        return MacaoResult.Success(
            ProviderData("", "", "", "")
        )
    }

    override suspend fun updateProfile(
        displayName: String,
        photoUrl: String
    ): MacaoResult<MacaoUser, AccountError> {
        TODO("Not yet implemented")
    }

    override suspend fun updateFullProfile(
        displayName: String?,
        country: String?,
        photoUrl: String?,
        phoneNo: String?,
        facebookLink: String?,
        linkedIn: String?,
        github: String?
    ): MacaoResult<UserData, AccountError> {
        TODO("Not yet implemented")
    }


    override suspend fun updateEmail(newEmail: String): MacaoResult<MacaoUser, AccountError> {
        TODO("Not yet implemented")
    }

    override suspend fun updatePassword(newPassword: String): MacaoResult<MacaoUser, AccountError> {
        TODO("Not yet implemented")
    }

    override suspend fun sendEmailVerification(): MacaoResult<MacaoUser, AccountError> {
        TODO("Not yet implemented")
    }

    override suspend fun sendPasswordReset(): MacaoResult<MacaoUser, AccountError> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(): MacaoResult<Unit, AccountError> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchUserData(): MacaoResult<UserData, AccountError> {
        TODO("Not yet implemented")
    }

    override suspend fun checkAndFetchUserData(): MacaoResult<UserData, AccountError> {
        println(" AuthPluginEmpty::checkAndFetchUserData() has been called")
        return MacaoResult.Success(
            UserData()
        )
    }

    override suspend fun signOut(): MacaoResult<Unit, AccountError> {
        println(" AuthPluginEmpty::logoutUser() has been called")
        return MacaoResult.Success(Unit)
    }

}