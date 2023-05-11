<script>
  import InfoBox from "../InfoBox.svelte";
  import Cloud from "../icons/Cloud.svelte";
  import Heart from "../icons/Heart.svelte";
  import Meteor from "../icons/Meteor.svelte";
  import Moon from "../icons/Moon.svelte";
  import Sun from "../icons/Sun.svelte";
  import Wind from "../icons/Wind.svelte";
</script>

<div style="display: flex; justify-content: center; margin-top: 5rem;">
  <div class="inner">
    <div class="grid-3">
      <InfoBox
        goTo="/docs/libraries/"
        title="Our Libraries"
        text="Learn more about what libraries that we are building and how they can be useful for you."
        ><Sun /></InfoBox
      >
      <InfoBox
        goTo="/docs/getting-started"
        title="Getting Started"
        text="Get started by installing Kryptokrona Kotlin SDK into your Maven/Gradle project."
        ><Wind /></InfoBox
      >
      <InfoBox
        goTo="/docs/contribute/"
        title="Contribute"
        text="If you would like to contribute to this project here are a couple of ways."
        ><Meteor /></InfoBox
      >

      <InfoBox
        goTo="/docs/other/about"
        title="About"
        text="Learn more about Kryptokrona Kotlin SDK."><Moon /></InfoBox
      >

      <InfoBox
        goTo="/docs/contribute/donate"
        title="Donate"
        text="If you want to support our project you can donate XKR."
        ><Heart /></InfoBox
      >

      <InfoBox
        goTo="/docs/other/additional-reading"
        title="Additional reading"
        text="Information regarding usage, relevant news or articles from the internet."
        ><Cloud /></InfoBox
      >
    </div>
  </div>
</div>

<style>
  .inner {
    max-width: 1152px;
  }
  @media (min-width: 950px) {
    .inner {
      width: 70vw;
    }
  }

  .grid-3 {
    display: grid;
    grid-template-columns: repeat(3, minmax(0, 1fr));
    gap: 1rem;
  }

  @media (max-width: 576px) {
    .grid-3 {
      grid-template-columns: repeat(1, minmax(0, 1fr));
    }
  }

  @media (max-width: 768px) and (min-width: 576px) {
    .grid-3 {
      grid-template-columns: repeat(2, minmax(0, 1fr));
    }
  }
</style>
