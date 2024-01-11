from typing import Dict, Any
import torch
from torch import nn
from labml_nn.optimizers import WeightDecay, GenericAdaptiveOptimizer
from labml_nn.optimizers.radam import RAdam


class AdaBelief(RAdam):
    def __init__(self, params, lr=1e-3, betas=(0.9, 0.999), eps=1e-16,
                 weight_decay: WeightDecay = WeightDecay(),
                 amsgrad = False,
                 degenerate_to_sgd = True,
                 rectify = True, defaults = None):
        defaults = {} if defaults is None else defaults
        super().__init__(params, lr, betas, eps, weight_decay, amsgrad, degenerate_to_sgd, defaults)
        self.rectify = rectify

    def init_state(self, state: Dict[str, any], group: Dict[str, any], param: nn.Parameter):
        state['step'] = 0
        state['exp_avg'] = torch.zeros_like(param, memory_format=torch.preserve_format)
        state['exp_avg_var'] = torch.zeros_like(param, memory_format=torch.preserve_format)
        if group['amsgrad']:
            state['max_exp_avg_var'] = torch.zeros_like(param, memory_format=torch.preserve_format)

    def get_ms(self, state: Dict[str, Any], group: Dict[str, Any], grad: torch.Tensor):
        beta1, beta2 = group['betas']
        m, s = state['exp_avg'], state['exp_avg_var']
        m.mul_(beta1).add_(grad, alpha=1 - beta1)
        grad_residual = grad - m
        s.mul_(beta2).addcmul_(grad_residual, grad_residual, value=1 - beta2)
        if group['amsgrad']:
            s_max = state['max_exp_avg_var']
            torch.maximum(s_max, s, out=s_max)
            return m, s_max
        else:
            return m, s

    def step_param(self, state: Dict[str, any], group: Dict[str, any], grad: torch.Tensor, param: torch.nn.Parameter):
        grad = self.weight_decay(param, grad, group)
        m, s = self.get_ms(state, group, grad)
        state['step'] += 1
        if not self.rectify:
            self.adam_update(state, group, param, m, s + group['eps'])
        else:
            self.r_adam_update(state, group, param, m, s + group['eps'])