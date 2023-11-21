package com.example.mathgame.module

import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize

@VersionedParcelize
abstract class User(var name: String, var operator: String):Parcelable
