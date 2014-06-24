package com.talsoft.organizeme.web.link;

/**
 * Lien simple<br/>
 * Lien de fil d'arianne (breadcrumb) ou bien de menu, etc.
 */
public class SimpleLink {

	/**
	 * Intitulé du lien
	 */
	private String label;
	/**
	 * Path du lien
	 */
	private String href;
	/**
	 * Classe css à appliquer au lien
	 */
	private String cssClass;

	/**
	 * @param label
	 * @param href
	 */
	public SimpleLink(String label, String href) {
		this.label = label;
		this.href = href;
		// Pas de classe css
		this.cssClass = "";
	}

	public SimpleLink(String label, String href, String cssClass) {
		this.label = label;
		this.href = href;
		this.cssClass = cssClass;
	}

	public String getLabel() {
		return label;
	}

	public String getHref() {
		return href;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

}
