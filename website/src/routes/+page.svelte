<script context="module">
    import External from "../components/icons/External.svelte";
    import Boxes from "../components/sections/Boxes.svelte";
    import Footer from "../components/Footer.svelte";
    import { page } from "$app/stores";
    export const frontmatter = {
    home: false,
  };
</script>

<svelte:head>
  <title>Kryptokrona Kotlin SDK</title>
  <meta name="description" content="The most fully featured implementation of the Kryptokrona Network protocols." /> 
  <meta property="og:site_name" content="Kryptokrona Kotlin SDK"/>
		<meta property="og:type" content="website" />
		<meta property="og:title" content="Kryptokrona Kotlin SDK" />
		<meta property="og:image" content={$page.url + "/img/sdklogo.png"}>
		<meta
		  property="og:description"
		  content="The most fully featured implementation of the Kryptokrona Network protocols."
		/>
    <meta property="og:locale" content="en"/>
    <meta property="og:url" content="https://sdk.kryptokrona.org/"/>
</svelte:head>

<div class="home-page">
  <div class="title-homepage">
    <div class="intro-homepage">
      <div class="gradient-title-homepage">
        Kryptokrona <span style="display: block">Kotlin SDK</span>
      </div>
      <div class="description-homepage">
        The most fully featured implementation of the Kryptokrona Network
        protocols
      </div>
      <div class="tagline-homepage">
        Kryptokrona Kotlin SDK includes many useful libraries and tools for
        building decentralized private communication and payment systems.
      </div>
    </div>
    <div class="hero-image-homepage">
      <img
        src="./img/sdklogo.png"
        alt=""
        style="width: 15rem; max-width: 220px;"
      />
    </div>
  </div>

  <div class="actions-homepage">
    <a
      href="https://github.com/kryptokrona/kryptokrona-kotlin-sdk"
      class="action-homepage action-primary-homepage"
    >
      <span class="label-homepage">View on GitHub </span>
      <div class="external-icon-homepage"><External /></div>
    </a>
    <a href="/docs" class="action-homepage action-flat-homepage">
      <span class="label-homepage">Read the docs</span>
    </a>
  </div>
</div>

<Boxes />
<div style="padding-bottom: 8rem;">
</div>
<Footer />
