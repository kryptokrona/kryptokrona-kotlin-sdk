<script context="module">
  import External from "../components/icons/External.svelte";
  import Boxes from "../components/sections/Boxes.svelte";
  import Footer from "../components/Footer.svelte";

  export const frontmatter = {
    title: "Some title",
    description: "Some description",
    home: false,
  };
</script>

<div class="home-page">
  <div class="title">
    <div class="intro">
      <div class="gradient-title">
        Kryptokrona <span style="display: block">Kotlin SDK</span>
      </div>
      <div class="description">
        The most fully featured implementation of the Kryptokrona Network
        protocols
      </div>
      <div class="tagline">
        Kryptokrona Kotlin SDK includes many useful libraries and tools for
        building decentralized private communication and payment systems.
      </div>
    </div>
    <div class="hero-image">
      <img
        src="./img/sdklogo.png"
        alt=""
        style="width: 15rem; max-width: 220px;"
      />
    </div>
  </div>

  <div class="actions">
    <a
      href="https://github.com/kryptokrona/kryptokrona-kotlin-sdk"
      class="action action-primary"
    >
      <span class="label">View on GitHub </span>
      <div class="external-icon"><External /></div>
    </a>
    <a href="/docs" class="action action-flat">
      <span class="label">Read the docs</span>
    </a>
  </div>
</div>

<Boxes />

<Footer />

<style>
  .action-flat {
    background-color: var(--box-bg);
    color: var(--text-color);
  }
  .external-icon {
    margin-left: 0.5rem;
    display: flex;
    align-items: center;
    font-size: 1.5rem;
  }
  .action-primary {
    --un-gradient: 45deg, #8b5cf6, #ec4899;
    color: var(--text-inverted);
    background-image: linear-gradient(
      var(--un-gradient, var(--un-gradient-stops, rgba(255, 255, 255, 0)))
    );
  }
  .action {
    height: 3rem;
    display: inline-flex;
    align-items: center;
    border-radius: 1.5rem;
    padding-left: 1.5rem;
    padding-right: 1.5rem;
    line-height: 3rem;
    transition-property: color, background-color, border-color,
      text-decoration-color, fill, stroke, opacity, box-shadow, transform,
      filter, backdrop-filter;
    transition-duration: 0.3s;
    transition-property: box-shadow;
    transition-timing-function: cubic-bezier(0.4, 0, 0.2, 1);
    transition-duration: 0.15s;
  }
  .actions {
    max-width: none;
    display: flex;
    justify-content: flex-start;
    padding-left: 0rem;
    padding-right: 0rem;
    margin-left: auto;
    margin-right: auto;
    margin-top: 1rem;
    gap: 1rem;
  }
  .hero-image {
    grid-column: span 5 / span 5;
    grid-column-start: span 5;
    grid-column-start: 8;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .tagline {
    margin-top: 1rem;
    color: rgba(100, 116, 139, 1);
    font-size: 1.5rem;
    font-weight: 400;
    line-height: 2.25rem;
  }
  .description {
    font-size: 2rem;
    line-height: 2.5rem;
  }
  .gradient-title {
    --un-gradient: 45deg, #8b5cf6, #ec4899;
    background-image: linear-gradient(
      var(--un-gradient, var(--un-gradient-stops, rgba(255, 255, 255, 0)))
    );
    -webkit-background-clip: text;
    background-clip: text;
    color: transparent;
  }
  .intro {
    grid-column: span 7 / span 7;
    grid-row-start: 1;
    text-align: left;
    font-size: 4rem;
  }
  .title {
    display: grid;
    grid-template-columns: repeat(12, minmax(0, 1fr));
    font-weight: 700;
    line-height: 6rem;
    font-size: 4rem;
  }
  .home-page {
    width: 70vw;
    padding-left: 0rem;
    padding-right: 0rem;
    margin-left: auto;
    margin-right: auto;
    max-width: 1152px;
    padding-top: 1rem;
  }

  @media (max-width: 992px) {
    .home-page {
      width: auto;
      margin-left: auto;
      margin-right: auto;
      max-width: 1152px;
      padding-left: 1rem;
      padding-right: 1rem;
      padding-top: 1rem;
    }
    .label {
      flex-grow: 1;
      text-align: center;
    }
    .actions {
      display: grid;
      grid-template-columns: repeat(1, minmax(0, 1fr));
      margin-left: auto;
      margin-right: auto;
      margin-top: 1rem;
      max-width: 320px;
      justify-content: center;
      grid-gap: 1rem;
      gap: 1rem;
      padding-left: 2.5rem;
      padding-right: 2.5rem;
    }
    .intro {
      grid-column: span 12 / span 12;
      grid-column-start: span 12;
      grid-column-start: 1;
      grid-row-start: 2;
      text-align: center;
    }
    .hero-image {
      grid-column: span 6 / span 6;
      grid-column-start: span 6;
      grid-column-start: 4;
      display: flex;
      align-items: center;
      justify-content: center;
    }
  }
</style>
