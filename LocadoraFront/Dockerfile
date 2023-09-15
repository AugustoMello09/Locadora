FROM node:latest as node
WORKDIR /app
COPY . . 
RUN npm install --silent
RUN npm run build --prod

FROM nginx:alpine
COPY --from=node app/dist/locadora-front /usr/share/nginx/html

