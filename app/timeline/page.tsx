"use client"

import React from 'react'
import {
  VerticalTimeline,
  VerticalTimelineElement,
} from 'react-vertical-timeline-component'
import 'react-vertical-timeline-component/style.min.css'
import { BriefcaseIcon, AcademicCapIcon, StarIcon } from '@heroicons/react/24/solid'

const timelineData = [
  {
    type: 'work',
    date: '2024 - presente',
    title: 'Desenvolvedor Frontend',
    subtitle: 'Remoto',
    description: 'Construção e manutenção da interface, integração com APIs e componentes reutilizáveis.',
    icon: 'work',
  },
  {
    type: 'work',
    date: '2022 - 2024',
    title: 'Engenheiro de UI',
    subtitle: 'Agência X',
    description: 'Design system, acessibilidade e performance.',
    icon: 'work',
  },
  {
    type: 'education',
    date: '2019 - 2021',
    title: 'Curso Avançado de Frontend',
    subtitle: 'Plataforma Y',
    description: 'JavaScript moderno, React e melhores práticas.',
    icon: 'school',
  },
]

function Icon({ kind }: { kind: string }) {
  if (kind === 'work') return <BriefcaseIcon className="h-5 w-5 text-white" />
  if (kind === 'school') return <AcademicCapIcon className="h-5 w-5 text-white" />
  return <StarIcon className="h-5 w-5 text-white" />
}

export default function TimelinePage() {
  return (
    <main className="min-h-screen bg-gray-900 py-12">
      <div className="container mx-auto px-4">
        <div className="max-w-4xl mx-auto text-center mb-8">
          <h1 className="text-3xl font-bold text-white sm:text-4xl">Minha Timeline</h1>
          <p className="mt-2 text-gray-300">Veja os eventos importantes organizados cronologicamente.</p>
        </div>

        <div className="bg-transparent p-4 sm:p-6 rounded-lg">
          <VerticalTimeline>
            {timelineData.map((item, idx) => (
              <VerticalTimelineElement
                key={idx}
                className={`vertical-timeline-element--${item.type}`}
                contentStyle={{ background: '#0f1724', color: '#fff', boxShadow: 'none' }}
                contentArrowStyle={{ borderRight: '7px solid #0f1724' }}
                date={item.date}
                iconStyle={{ background: item.type === 'work' ? '#4f46e5' : '#db2777', color: '#fff' }}
                icon={<Icon kind={item.icon} />}
              >
                <h3 className="text-lg font-semibold text-white">{item.title}</h3>
                <h4 className="text-sm text-gray-300">{item.subtitle}</h4>
                <p className="mt-2 text-gray-300">{item.description}</p>
              </VerticalTimelineElement>
            ))}

            <VerticalTimelineElement
              iconStyle={{ background: '#10b981', color: '#fff' }}
              icon={<StarIcon className="h-5 w-5 text-white" />}
            />
          </VerticalTimeline>
        </div>
      </div>
    </main>
  )
}
