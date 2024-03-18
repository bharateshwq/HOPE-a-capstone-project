import { Carousel, Typography, Button } from "@material-tailwind/react";
import { Link, useNavigate } from "react-router-dom";
 
export function CarouselWithContent() {
  const navigate = useNavigate();
 
  const handleClick = (sectionId) => {
    navigate(`/about#${sectionId}`);
  };
 
  return (
    <Carousel className="rounded-xl">
      <div className="relative h-full w-full">
        <img
          src="https://th.bing.com/th/id/R.d75c4065c480e76ab3dc4256d56841ba?rik=0e0J%2bUWR1DwOkw&riu=http%3a%2f%2fcrowellu.com%2fwp-content%2fuploads%2f2016%2f07%2fhope.jpg&ehk=vi6EJIeYP2RVQ51yznNgmddS6tXL%2b97q9g29G%2bdKPuU%3d&risl=&pid=ImgRaw&r=0"
          alt="image 1"
          className="h-full w-full object-cover"
        />
        <div className="absolute inset-0 grid h-full w-full place-items-center bg-black/60">
          <div className="w-3/4 mt-6 text-center md:w-2/4">
            <Typography
              variant="h1"
              color="white"
              className="mb-8 text-3xl md:text-4xl lg:text-5xl"
            >
             {/* <p>Welcome to HOPE</p>  */}
              <p>Help Others Prosper Eternally</p>
            </Typography>
            <Typography
              variant="lead"
              color="white"
              className="mb-12 opacity-80"
            >
             Your generosity fuels our mission to make a difference in the lives of those in need,
             fostering hope and empowerment.
             Together, we can create positive change and build a brighter future for all.
            </Typography>
            <div className="flex justify-center gap-2">
            <Button size="lg" color="white" onClick={() => handleClick('about')}>
                Explore
              </Button>
              {/* <Button size="lg" color="white" variant="text">
                Gallery
              </Button> */}
            </div>
          </div>
        </div>
      </div>
      <div className="relative h-full w-full">
        <img
          // src="https://t3.ftcdn.net/jpg/01/65/52/90/360_F_165529054_psP772fyIRhhyixzsp0pLsdNJbvX8gRn.jpg"
          // src= "https://www.kantar.com/latin-america/-/media/project/kantar/latin-america/sostentabilidad-corporativa.jpeg"
          src="https://img.freepik.com/premium-photo/hand-holding-pile-soil-with-plant-growing-out_145644-18268.jpg"
          alt="image 2"
          className="h-full w-full object-cover"
        />
        <div className="absolute inset-0 grid h-full w-full items-center bg-black/">
          <div className="w-3/4 pl-12 md:w-2/4 md:pl-20 lg:pl-32">
            <Typography
              variant="h1"
              color="white"
              className="mb-4 text-3xl md:text-4xl lg:text-5xl"
            >
              Join Hands, Change Lives: Volunteer Today!
            </Typography>
            <Typography
              variant="lead"
              color="white"
              className="mb-12 opacity-80"
            >
              It's about lending a helping hand, offering hope, and being the light in someone's darkest hour.
              Through volunteering, you not only enrich others' lives but also find fulfillment in your own, knowing you've made a meaningful difference.
            </Typography>
            <div className="flex gap-2">
              <Button size="lg" color="white" onClick={() => handleClick('volunteer')}>
                Explore
              </Button>
              {/* <Button size="lg" color="white" variant="text">
                Gallery
              </Button> */}
            </div>
          </div>
        </div>
      </div>
      <div className="relative h-full w-full">
        <img
          src="https://scx2.b-cdn.net/gfx/news/hires/2015/takingtoughd.jpg"
          alt="image 3"
          className="h-full w-full object-cover"
        />
        <div className="absolute inset-0 grid h-full w-full items-end bg-black/35 align-right">
          {/* <div className="w-3/4 pr-12 pb-12 md:w-2/4 md:pr-20 md:pb-20 lg:pr-32 lg:pb-32 text-right"> */}
          <div className="w-3/4 pl-12 pb-12 md:w-2/4 md:pl-20 md:pb-20 lg:pl-32 lg:pb-32">
          {/* <div className="w-full md:w-3/4 lg:w-2/4 md:pr-20 lg:pr-32"> */}
 
            <Typography
              variant="h1"
              color="white"
              className="mb-4 text-3xl md:text-4xl lg:text-5xl"
            >
              Every Contribution Counts.
            </Typography>
            <Typography
              variant="lead"
              color="white"
              className="mb-12 opacity-80"
            >
              Embrace the power of giving!
              Every donation, regardless of its size, makes a meaningful difference.
              Join us in the journey of giving,
              where each contribution becomes a beacon of hope,
              guiding humanity towards a better, more compassionate world.
            </Typography>
            <div className="flex gap-2">
              <Button size="lg" color="white" onClick={() => handleClick('donation')}>
                Explore
              </Button>
              {/* <Button size="lg" color="white" variant="text">
                Gallery
              </Button> */}
            </div>
          </div>
        </div>
      </div>
    </Carousel>
  );
}