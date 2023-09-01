import { initializeApp } from "firebase/app";
import {getAuth, FacebookAuthProvider } from "firebase/auth";
import { GoogleAuthProvider } from "firebase/auth";


const firebaseConfig = {
  apiKey: "AIzaSyBipDFonwdrJGxGeub4SppSSlL9E8jnagE",
  authDomain: "qlgh-544d4.firebaseapp.com",
  projectId: "qlgh-544d4",
  storageBucket: "qlgh-544d4.appspot.com",
  messagingSenderId: "393468024298",
  appId: "1:393468024298:web:19eef978267d5bff1dce93",
  measurementId: "G-ELSE193NVG"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const auth = getAuth(app);
const provider = new FacebookAuthProvider();
const providerGg= new GoogleAuthProvider();
export {auth,provider,providerGg};