package org.elixir_lang.navigation.item_presentation.modular;

import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import org.elixir_lang.icons.ElixirIcons;
import org.elixir_lang.navigation.item_presentation.Parent;
import org.elixir_lang.psi.call.Call;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class Module implements ItemPresentation, Parent {
    /*
     * Static Methods
     */

    @Contract(pure = true)
    @NotNull
    public static String presentableText(Call call) {
        PsiElement[] primaryArguments = call.primaryArguments();

        assert primaryArguments != null && primaryArguments.length > 0;

        return primaryArguments[0].getText();
    }

    /*
     * Fields
     */

    @NotNull
    protected final Call call;
    @Nullable
    private final String location;

    /*
     * Constructors
     */

    /**
     *
     * @param location the parent name of the Module that scopes {@code call}; {@code null} when scope is {@code quote}.
     * @param call a {@code Kernel.defmodule/2} call nested in {@code parent}.
     */
    public Module(@Nullable final String location, @NotNull final Call call) {
        this.call = call;
        this.location = location;
    }

    /*
     * Public Instance Methods
     */

    /**
     * Returns the name of the object to be presented in most renderers across the program.
     *
     * @return the function name.
     */
    @Override
    @NotNull
    public String getPresentableText() {
        return presentableText(call);
    }

    /**
     * Combines {@link #getLocationString()} with {@link #getPresentableText()} for when this Module is the parent of
     * another Module and needs to act as the location of the child Module.
     *
     * @return {@link #getLocationString()} + "." + {@link #getPresentableText()} if {@link #getLocationString()} is not
     *   {@code null}; otherwise, {@link #getPresentableText()}.
     */
    @Override
    @NotNull
    public String getLocatedPresentableText() {
        String locatedPresentableText;
        String locationString = getLocationString();

        if (locationString != null) {
            locatedPresentableText = locationString + "." + getPresentableText();
        } else {
            locatedPresentableText = getPresentableText();
        }

        return locatedPresentableText;
    }

    /**
     * Returns the location of the object (for example, the package of a class). The location
     * string is used by some renderers and usually displayed as grayed text next to the item name.
     *
     * @return the location description, or null if none is applicable.
     */
    @Nullable
    @Override
    public String getLocationString() {
        return location;
    }

    /**
     * The module icon.
     *
     * @param unused Used to mean if open/close icons for tree renderer. No longer in use. The parameter is only there for API compatibility reason.
     */
    @Override
    @NotNull
    public Icon getIcon(boolean unused) {
        return ElixirIcons.MODULE;
    }
}
