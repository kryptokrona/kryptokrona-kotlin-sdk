<script>
  import InfoBox from "../InfoBox.svelte";
</script>

<div style="display: flex; justify-content: center; margin-top: 5rem">
  <div class="inner">
    <div class="grid-3">
      <InfoBox
        goTo="/guide/getting_started"
        title="Getting started"
        text="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        ><img src="/icons/drop.svg" alt="droplet" /></InfoBox
      >
      <InfoBox
        goTo="/guide/contribute/"
        title="Contribute"
        text="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        ><img src="/icons/sun.svg" alt="droplet" /></InfoBox
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
