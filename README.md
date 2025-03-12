# Built by Pixel NewsUK Android Task Submission

Thanks for taking the time to review my submission for the Built by Pixel Android task.

This was a fun project that expanded upon my experience and I had a lot of fun making it.

I attempted to write clean, SOLID code, demonstrating separation of concerns, clean architecture and sound MVVM design decisions.  
The project is scalable, I included a navigation component in case more screens need to be added  

I chose the minSdk version to be 26 since the Times news app supports Android 8.0 and up.

Different UI states were considered by my use of `rememberSaveable`, using just `remember` would have lost a coin selection on orientation change

I attempted to animate the list by using a scroll to top button, which smoothly brings the user to the top of the list in order to refresh

---

### Improvements:

- I would like to get my ViewModel Tests working, currently they fail due to an error which I spent a long time trying to debug
- A filter/ search seems appropriate, the best coins to test with were the higher ranked ones (most of these had an image and description associated with them)
whereas some of the coins at the top of the alphabetised list don't
- I think perhaps the BottomSheet component would be more testable if it took more parameters to hoist state better (like passing the list of coins to the composable)

---

## Thanks again
