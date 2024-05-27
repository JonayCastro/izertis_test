FROM node:20.13-alpine
WORKDIR /APP
RUN apk add --no-cache python3
ENV PYTHON=/usr/bin/python3
COPY package*.json ./
RUN npm install
RUN npm install -g @angular/cli
COPY . /APP
RUN npm run build --dev
CMD ["npm", "run", "start"]
