<template>
	<v-dialog
		:model-value="true"
		max-width="1350"
		persistent
	>
		<v-card>
			<v-card-title>
				<v-container>
					<v-row>
						<v-col
							cols="11"
						>
							<h2>
                10 Atores (Atrizes) Filmes Mais Premiados
              </h2>
						</v-col>

						<v-col
							cols="1"
							justify="end"
						>
							<v-btn
								variant="text"
								density="comfortable"
								icon="mdi-close"
								@click="closeModal"
							/>
						</v-col>
					</v-row>
				</v-container>
			</v-card-title>

			<v-card-text>
				<v-container
          fluid
        >
					<v-row
            justify="center"
          >
            <v-col
              cols="10"
            >
              <Bar
                id="my-chart-id"
                :data="chartData"
                :options="chartOptions"
              />
            </v-col>
          </v-row>
				</v-container>
			</v-card-text>
		</v-card>
	</v-dialog>
</template>

<script>
import axios from 'axios'

import { Bar } from 'vue-chartjs'
import { Chart as ChartJS, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale } from 'chart.js'

ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale)

export default {
  name: 'MostAwardedPerson',
  components: {
    Bar,
  },
  data()  {
    return {
      actors: [],
    }
  },
  computed: {
      chartData() {
        return {
          labels: this.getLabels(),
          datasets: [
            { 
              data: this.getValues(),
              label: 'Número de Premiações',
            },
          ]
        }
      },
      chartOptions() {
        return {}
      }
    },
  async mounted() {
    const responseMostAwardedActors = await axios.get('/pessoa/mais_premiadas')
    this.actors = responseMostAwardedActors.data
  },
  methods: {
    closeModal() {
			this.$emit('closeModal')
    },
    getLabels() {
      return this.actors.map((actor) => {
        return actor.pessoa_nome_art
      })
    },
    getValues() {
      return this.actors.map((actor) => {
        return actor.total_premios
      })
    },
  },
}
</script>
