//
//  ToastHintUtil.h
//  sourcing
//
//  Created by wuzhu on 12-12-28.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface ToastHintUtil : NSObject
+(void)showHint:(NSString *)msg parentview:(UIView *)view;
+(void)showHint:(NSString *)msg;
+(void)showhintSucess:(NSString *)msg;
+(void)showHintError:(NSString *)msg;
@end
