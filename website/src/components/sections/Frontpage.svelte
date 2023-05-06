<script>
  import InfoBox from "../InfoBox.svelte";
  import Meteor from "../icons/Meteor.svelte";
  import Sun from "../icons/Sun.svelte";
  import Wind from "../icons/Wind.svelte";
</script>

<div style="display: flex; justify-content: center; margin-top: 5rem">
  <div class="inner">
    <div class="grid-3">
      <InfoBox
        goTo="/guide/libraries/"
        title="Our libraries"
        text="Our libraries that are being built in this SDK is for various purposes. Here we are going to get into what they do and what you can expect from each."
        ><Sun /></InfoBox
      >
      <InfoBox
        goTo="/guide/getting_started"
        title="Getting started"
        text="Install Kryptokrona Kotlin SDK into your Maven/Gradle project."
        ><Wind /></InfoBox
      >
      <InfoBox
        goTo="/guide/contribute/"
        title="Contribute"
        text="If you would like to contribute to this project here are a couple of ways."
        ><Meteor /></InfoBox
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
