package org.elixir_lang.psi;

import com.intellij.psi.PsiElement;

import java.util.List;

/**
 * Created by luke.imhoff on 1/19/15.
 */
public interface Heredoc extends Fragmented, Parent, PsiElement, Quotable {
    public ElixirHeredocPrefix getHeredocPrefix();

    public List<HeredocLine> getHeredocLineList();
}
